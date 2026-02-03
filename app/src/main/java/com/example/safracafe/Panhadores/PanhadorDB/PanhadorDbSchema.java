package com.example.safracafe.Panhadores.PanhadorDB;

public class PanhadorDbSchema{
    public static final class PanhadorTbl{
        public static final String NOME_TBL = "panhadores";
        public static final class Cols{
            public static final String ID_PANHADOR = "_id";
            public static final String NOME_PANHADOR = "nomePanhador";
            public static final String CPF_PANHADOR = "cpfPanhador";
            public static final String NUMERO_PANHADOR = "numeroPanhador";
            public static final String CHAVE_PIX = "chavePix";
            public static final String TOTAL_COLHIDO = "totalColhido";
            public static final String ACERTO = "acerto";
        }
    }
}