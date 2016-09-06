package call.quiamco.com.midtermproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Cart> {
    private Context mContext;
    private int mResLayoutId;
    private List<Cart> mShoppingCart;

    public CartAdapter(Context context, int resLayoutId, List<Cart> shopp){
        super(context, resLayoutId, shopp);

        mContext = context;
        mResLayoutId = resLayoutId;
        mShoppingCart = shopp;
    }

    private class Holder{

        TextView tvItem_name;
        TextView tvQuantity;
        TextView tvPrice;
        TextView tvTotal;


        public Holder(View view){
            tvItem_name = (TextView) view.findViewById(R.id.tvItemName);
            tvQuantity = (TextView) view.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvTotal = (TextView) view.findViewById(R.id.tvTotalPrice);

        }
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {

        Holder holder;
        if (convert == null){
            convert = LayoutInflater.from(mContext).inflate(mResLayoutId, parent, false);
            holder = new Holder(convert);
            convert.setTag(holder);
        } else {
            holder = (Holder)convert.getTag();

        }


        Cart shop = mShoppingCart.get(position);
        if (shop!=null) {
            if (holder.tvItem_name != null){
                holder.tvItem_name.setText("Item Name: "+shop.getmItm_name()+"");
            }
            if (holder.tvQuantity !=null){
                holder.tvQuantity.setText("Quantity: "+shop.getmQty()+"");

            }
            if (holder.tvPrice !=null){
                holder.tvPrice.setText("Price: "+(int) shop.getmPrice()+"");

            }
            if (holder.tvTotal !=null){
                holder.tvTotal.setText("Total Price: "+(int) shop.getmTotalPrice()+"");

            }


        }

        return convert;
    }



}
