package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.flyme.meditation.lifeassistant.bean.SourceBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;
import com.flyme.meditation.lifeassistant.database.ClazzBean;

/**
 * Created by kangweodai on 21/07/17.
 */

public class SourcePriceFragment extends Fragment {

    public static final String ARG_SOURCE = "sources";
    public static final String ARG_TICKET = "ticket";

    private ListView mList;
    private BaseAdapter mAdapter;

    private SourceBean mSource;
    private TicketBean mTicket;

    public static SourcePriceFragment newInstance(SourceBean source, TicketBean ticket) {
        SourcePriceFragment fragment = new SourcePriceFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SOURCE, source);
        args.putSerializable(ARG_TICKET, ticket);
        fragment.setArguments(args);
        return fragment;
    }

    public SourcePriceFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSource = (SourceBean) getArguments().getSerializable(ARG_SOURCE);
        mTicket = (TicketBean) getArguments().getSerializable(ARG_TICKET);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_source_price, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = (ListView) view.findViewById(android.R.id.list);
        mAdapter = new PriceAdapter();
        mList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private class PriceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mSource.getClazzs() != null ? mSource.getClazzs().size() : 0;
        }

        @Override
        public ClazzBean getItem(int position) {
            return mSource.getClazzs() != null ? mSource.getClazzs().get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity())
                        .inflate(R.layout.item_ticket_price, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ClazzBean clazz = getItem(position);
            if (clazz == null) {
                return null;
            }

            holder.tvPrice.setText("￥" + clazz.getPrice());
            holder.tvClass.setText(clazz.getClazz());

            holder.btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BookActivity.class);
                    intent.putExtra(BookActivity.EXTRA_TICKET, mTicket);
                    intent.putExtra(BookActivity.EXTRA_SOURCE, mSource);
                    intent.putExtra(BookActivity.EXTRA_CLAZZ, clazz);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvPrice;
        TextView tvClass;
        Button btnBook;

        public ViewHolder(View view) {
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvClass = (TextView) view.findViewById(R.id.tv_class);
            btnBook = (Button) view.findViewById(R.id.btn_book);
        }
    }
}
