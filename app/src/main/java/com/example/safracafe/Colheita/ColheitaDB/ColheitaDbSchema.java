package com.example.safracafe.Colheita.ColheitaDB;

public class ColheitaDbSchema {
    public static final class ColheitasTbl{
        public static final String NOME_TBL = "colheitas";
        public static final class Cols{
            public static final String ID_COLHEITA = "_id";
            public static final String ID_LAVOURA_COLHEITA = "idLavouraColheita";
            public static final String ID_TAlHAO_COLHEITA = "idTalhaoColheita";
            public static final String ID_PANHADOR_COLHEITA = "idPanhadorColheita";
            public static final String QUANTIDADE = "quantidadeColheita";
            public static final String DATA = "dataColheita";
        }
    }
}

