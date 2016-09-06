package call.quiamco.com.midtermproject;

/**
 * Created by Deceree on 05/09/2016.
 */
public class Cart {

    private String mItm_name;
    private int mQty;
    private double mPrice;
    private double mTotalPrice;

    public Cart() {

    }

    public Cart(String mItem_name, int mQty, double mPrice, double mTotalPrice) {
        this.mItm_name = mItem_name;
        this.mQty = mQty;
        this.mPrice = mPrice;
        this.mTotalPrice = mTotalPrice;
    }

    public String getmItm_name() {
        return mItm_name;
    }

    public void setmItm_name(String mItm_name) {
        this.mItm_name = mItm_name;
    }

    public int getmQty() {
        return mQty;
    }

    public void setmQtity(int mQuantity) {
        this.mQty = mQuantity;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public double getmTotalPrice() {
        return mTotalPrice;
    }

    public void setmTotalPrice(double mTotalPrice) {
        this.mTotalPrice = mTotalPrice;
    }
}


