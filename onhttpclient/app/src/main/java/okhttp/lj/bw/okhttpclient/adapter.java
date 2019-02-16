package okhttp.lj.bw.okhttpclient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/*
  name:刘江
  data:2019
*/public class adapter extends BaseAdapter {
    List<Bean.ResultBean> list;
    Context context;

    public adapter(List<Bean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder viewholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.iteam, null);
            viewholder = new viewholder();
            viewholder.t1 = convertView.findViewById(R.id.text1);
            convertView.setTag(viewholder);
        } else {
            viewholder = (adapter.viewholder) convertView.getTag();
        }
        viewholder.t1.setText(list.get(position).getTitle());
        return convertView;
    }

    class viewholder {
        TextView t1;
    }
}
