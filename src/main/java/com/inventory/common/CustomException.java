package com.inventory.common;

public class CustomException {
    public static class TenantMissingException extends RuntimeException {
        public TenantMissingException() { super("Missing X-Tenant-Id header"); }
    }
    public static class CrossTenantAccessException extends RuntimeException {
        public CrossTenantAccessException() { super("Forbidden: cross-tenant access"); }
    }
}