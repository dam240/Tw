package com.Adapte;

import android.content.Context;

import com.com.cd.da51.model.User;
import com.example.da.myapplication.R;
import com.yuyh.easyadapter.abslistview.EasyLVAdapter;
import com.yuyh.easyadapter.abslistview.EasyLVHolder;

import java.util.List;

/**
 * Created by da on 18-2-11.
 */

public class UserAdapte extends EasyLVAdapter<User> {
    public UserAdapte(Context context, List<User> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    public UserAdapte(Context context, List<User> list) {
        super(context, list);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, User user) {
     holder.setText(R.id.l_id,user.getL_id()).setText(R.id.card,user.getCard())
     .setText(R.id.name,user.getName());

    }
}
