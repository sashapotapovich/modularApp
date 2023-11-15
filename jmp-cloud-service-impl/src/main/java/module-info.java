import org.cource.service.api.impl.ServiceImpl;

module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    provides org.cource.service.api.Service with ServiceImpl;
}