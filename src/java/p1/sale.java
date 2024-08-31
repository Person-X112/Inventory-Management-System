package p1;

public class sale {

        
        private String parName, itemName;
        private int quantity, rate;

        public sale(String in, int quan, int rate, String pn) {
            this.itemName = in;
            this.parName = pn;
            this.quantity = quan;
            this.rate = rate;
            System.out.println("Contrustor Called");
        }

        public String getParName() {
            return parName;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getRate() {
            return rate;
        }
        
    }