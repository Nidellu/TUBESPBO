package tubespbo.Model;

public enum OrderStatusEnum {
    NOW{
        @Override
        public String toString() {
            return "DALAM PERJALANAN";
        }
    },
    CANCEL{
        @Override
        public String toString() {
            return "DICANCEL";
        }
    },
    FINISHED{
        @Override
        public String toString() {
            return "PERJALANAN SELESAI";
        }
    }
}
