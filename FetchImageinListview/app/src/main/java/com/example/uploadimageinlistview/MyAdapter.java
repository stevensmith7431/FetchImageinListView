package com.example.uploadimageinlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyList> mproduct;

    public MyAdapter(Context mContext, List<MyList> mproduct) {
        this.mContext = mContext;
        this.mproduct = mproduct;
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            final int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Errore durante il download da " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }


    @Override
    public int getCount() {
        return mproduct.size();
    }

    @Override
    public Object getItem(int position) {
        return mproduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v = View.inflate(mContext, R.layout.list_items, null);
        TextView text1 = v.findViewById(R.id.text1);
        TextView text2 = v.findViewById(R.id.text2);
        TextView text3 = v.findViewById(R.id.text3);
        TextView text4 = v.findViewById(R.id.text4);
        TextView text5 = v.findViewById(R.id.text5);
        TextView text6 = v.findViewById(R.id.text6);
        ImageView imageView = v.findViewById(R.id.imageid);

        final MyList listItem = mproduct.get(position);

        imageView.setImageBitmap(downloadBitmap(listItem.getImage()));
        text1.setText(mproduct.get(position).getName());
        text2.setText(mproduct.get(position).getStudno());
        text3.setText(mproduct.get(position).getDate());
        text4.setText(mproduct.get(position).getSession());
        text5.setText(mproduct.get(position).getDept());
        text6.setText(mproduct.get(position).getSection());

        return v;
    }
}
