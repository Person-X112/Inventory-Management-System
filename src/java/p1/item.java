package p1;

public class item {

        private int stock;
        private String itemName;

        public item(String in, int quan) {
            this.stock = quan;
            this.itemName = in;
        }

        public int getStock() {
            return stock;
        }

        public String getItemName() {
            return itemName;
        }

    }