module application {
    uses org.cource.service.api.Service;
    uses org.cource.api.BankInterface;
    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.webmvc;
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires jakarta.persistence;
    requires spring.web;
    opens org.cource.application;
    opens org.cource.application.controller;

}