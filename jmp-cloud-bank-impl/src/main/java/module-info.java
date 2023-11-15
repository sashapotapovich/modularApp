import org.cource.api.impl.BankInterfaceImpl;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    provides org.cource.api.BankInterface with BankInterfaceImpl;
}