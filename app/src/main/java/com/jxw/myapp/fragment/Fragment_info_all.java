package com.jxw.myapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxw.myapp.R;
import com.jxw.myapp.pojo.ListActivityBean;
import com.jxw.myapp.utils.xUtilsImageUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Fragment_info_all extends Fragment_info {
    private ListView lv_info;
    public final List<ListActivityBean.Info> infoList = new ArrayList<ListActivityBean.Info>();
    public List<Integer> choicelike = new ArrayList<Integer>();
    private BaseAdapter adapter;
    private ProgressBar pb_all;

//    private TextView tv_infoContent;
//    private TextView tv_infoName;
//    private TextView tv_infoDate;

    private static  final String TAG="Fragment_info_all";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        adapter = new BaseAdapter() {

            private ListView lv_info;

            @Override
            public int getCount() {
                return infoList.size();
            }

            @Override
            public Object getItem(int position) {

                return infoList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = View.inflate(getActivity().getApplicationContext(), R.layout.fragment_info_more, null);
                    viewHolder.tv_infoContent = ((TextView) convertView.findViewById(R.id.tv_infoContent));
                    viewHolder.tv_infoName = ((TextView) convertView.findViewById(R.id.tv_infoName));
                    viewHolder.tv_infoDate = ((TextView) convertView.findViewById(R.id.tv_infoDate));
                    viewHolder.iv_like = ((ImageView) convertView.findViewById(R.id.iv_like));
                    viewHolder.iv_like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (choicelike.contains((Integer) (((ImageView) v).getTag()))) {
                                ((ImageView) v).setImageResource(R.drawable.likebefore);
                                choicelike.remove((Integer) (((ImageView) v).getTag()));
                            } else {
                                Bitmap bitmap = BitmapFactory.decodeResource(Fragment_info_all.this.getResources(), R.drawable.likeafter);
                                ((ImageView) v).setImageBitmap(bitmap);
                                choicelike.add((Integer) (((ImageView) v).getTag()));
                            }
                        }
                    });
                    convertView.setTag(viewHolder);  //缓存对象将地址缓存起来
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.iv_like.setTag(position);
                if (choicelike.contains(position)) {
                    viewHolder.iv_like.setImageResource(R.drawable.likeafter);
                } else {
                    viewHolder.iv_like.setImageResource(R.drawable.likebefore);
                }
                ListActivityBean.Info info = infoList.get(position);

                viewHolder.iv_photoImg = ((ImageView) convertView.findViewById(R.id.iv_photoImg));
                xUtilsImageUtils.display(viewHolder.iv_photoImg,"http://10.0.2.2:8080/info/"+info.photoImg,true);
                //x.image().bind(viewHolder.iv_photoImg,"http://192.168.1.104/info/"+info.photoImg);
                //Log.i(TAG,"http://10.53.224.170/info/"+info.photoImg);

                //页面处理
                viewHolder.tv_infoName.setText(info.infoName);
                viewHolder.tv_infoContent.setText(info.infoContent);
                viewHolder.tv_infoDate.setText(info.infoDate);
                return convertView;

            }
        };
        View view1 = inflater.inflate(R.layout.fragment_info_all, null);
        pb_all = ((ProgressBar) view1.findViewById(R.id.pb_all));
        lv_info = ((ListView) view1.findViewById(R.id.lv_info));
        lv_info.setAdapter(adapter);
        return view1;
}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getinfolist();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_info = ((ListView) view.findViewById(R.id.lv_info));
        lv_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListActivityBean.Info infomore = infoList.get(position);
                Intent intent = new Intent(getActivity().getApplication(),one_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("infomore",infomore);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getinfolist() {
        RequestParams rp = new RequestParams("http://10.0.2.2:8080/info/getinfo");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                pb_all.setVisibility(View.GONE);
                System.out.println(result);
                Gson gson = new Gson();
                ListActivityBean bean = gson.fromJson(result, ListActivityBean.class);
                System.out.println(result);
                System.out.println(bean.status);
                System.out.println(bean.infoList.size());
                infoList.addAll(bean.infoList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(getActivity(),ex.toString(), Toast.LENGTH_SHORT).show();
                System.out.println(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private static class ViewHolder{
        TextView tv_infoContent;
        TextView tv_infoName;
        TextView tv_infoDate;
        ImageView iv_like;
        ImageView iv_photoImg;

    }
}
