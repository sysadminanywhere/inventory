package com.sysadminanywhere.inventory.service;

import com.sysadminanywhere.inventory.model.ComputerEntry;
import com.sysadminanywhere.inventory.model.SoftwareEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InventoryService {

    @Value("${ldap.host.username:}")
    String userName;

    @Value("${ldap.host.password:}")
    String password;

    ResolveService<ComputerEntry> resolveService = new ResolveService<>(ComputerEntry.class);

    private final LdapService ldapService;
    private final WmiService wmiService;

    public InventoryService(LdapService ldapService, WmiService wmiService) {
        this.ldapService = ldapService;
        this.wmiService = wmiService;
    }

    /*

     ┌───────────── second (0-59)
     │ ┌───────────── minute (0 - 59)
     │ │ ┌───────────── hour (0 - 23)
     │ │ │ ┌───────────── day of the month (1 - 31)
     │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
     │ │ │ │ │ │
     * * * * * *

    "0 0 12 * * *" every day at 12:00

    */

    @Scheduled(cron = "${cron.expression}")
    public void scan() {

        Boolean result = ldapService.login(userName, password);

        if (!result) {
            log.error("Unknown user: {}", userName);
            return;
        }

        List<ComputerEntry> computers = getComputers();

        for (ComputerEntry computerEntry : computers) {
            List<SoftwareEntity> software = getSoftware(computerEntry.getCn());
        }

    }

    public List<SoftwareEntity> getSoftware(String hostName) {
        try {
            WmiResolveService<SoftwareEntity> wmiResolveService = new WmiResolveService<>(SoftwareEntity.class);
            return wmiResolveService.GetValues(wmiService.execute(hostName, "Select * From Win32_Product"));
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<ComputerEntry> getComputers() {
        List<Entry> result = ldapService.search("(objectClass=computer)");
        return resolveService.getADList(result);
    }

}