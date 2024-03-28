package com.msbank.sale.core.constants;

public class Constants {

    public static class Rel {
        public static final String START_SALES = "api/v1/sales";
    }
    public static class Kafka {
        public static final String SALE_FINALIZE = "sale-finalize";
        public static final String SALE_CANCEL = "sale-cancel";
        public static final String SUCCESSFULLY_CONSUMED = "successfully consumed {}={}";
    }
    public static class Sale {

        public static final String SALE = "sale: {}";
        public static final String CANCELANDO_A_VENDA = "Cancelando a venda.";
        public static final String VENDA_CANCELADA = "Venda cancelada.";
        public static final String FINALIZANDO_A_VENDA = "Fínalizando a venda.";
        public static final String VENDA_FINALIZADA_C_SUCESSO = "Venda finalizada c/sucesso.";
    }
}
