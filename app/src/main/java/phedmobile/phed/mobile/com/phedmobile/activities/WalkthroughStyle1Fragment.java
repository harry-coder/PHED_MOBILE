package phedmobile.phed.mobile.com.phedmobile.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import  phedmobile.phed.mobile.com.phedmobile.R;
import  phedmobile.phed.mobile.com.phedmobile.tools.ImageViewCircleTransform;

/**
 * Created by wahyu on 15/11/16.
 */

@SuppressLint("ValidFragment")
public class WalkthroughStyle1Fragment extends Fragment {
    int wizard_page_position;

    public WalkthroughStyle1Fragment(int position) {
        this.wizard_page_position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout_id = R.layout.walkthrough1_fragment;
        View view = inflater.inflate(layout_id, container, false);

        String url =  "https://gojehotaschool.org/76-full.png";

        ImageView img = (ImageView) view.findViewById(R.id.imagePage1);
        loadImageRequest(img, url);

        return view;
    }

    private void loadImageRequest(ImageView img, String url) {
        Glide.with(getActivity())
                .load(url)
                .transform(new ImageViewCircleTransform(getActivity()))
//                .thumbnail(0.01f)
                .centerCrop()
                .into(img);
    }
}
