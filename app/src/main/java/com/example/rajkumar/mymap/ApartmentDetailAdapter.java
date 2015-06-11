package com.example.rajkumar.mymap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rajkumar on 09/06/15.
 */
public class ApartmentDetailAdapter extends ArrayAdapter<ApartmentDetails> {
    ArrayList<ApartmentDetails> detailList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;


    public ApartmentDetailAdapter(Context context, int resource, ArrayList<ApartmentDetails> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        detailList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);

            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
            holder.buildingDescription=(TextView)v.findViewById(R.id.buildingDescriptionn);
            holder.addressLine=(TextView)v.findViewById(R.id.addresstext);
            holder.localityName=(TextView)v.findViewById(R.id.location);
            holder.cityName=(TextView)v.findViewById(R.id.cityname);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.loadingthrobber);

        new DownloadImageTask(holder.imageview).execute(detailList.get(position).getLogoimage());
        holder.tvDescription.setText(detailList.get(position).getDescription());
        holder.buildingDescription.setText(detailList.get(position).getBuilderdescription());
        holder.addressLine.setText(detailList.get(position).getAddressline());
        holder.localityName.setText(detailList.get(position).getLocalityname());
        holder.cityName.setText(detailList.get(position).getCityname());

        return v;

    }

    static class ViewHolder {

        public ImageView imageview;

        public TextView tvDescription;

        public TextView buildingDescription;

        public TextView addressLine;

        public TextView localityName;

        public TextView cityName;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}