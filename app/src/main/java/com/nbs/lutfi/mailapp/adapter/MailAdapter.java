package com.nbs.lutfi.mailapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbs.lutfi.mailapp.R;
import com.nbs.lutfi.mailapp.model.MailModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lutfi on 3/22/2017.
 */

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {
    private List<MailModel> mails;
    private Context mailContext;

    public static class MailViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mailLayout;
        ImageView mailPict, mailStar;
        TextView mailSender, mailSubject, mailContent, mailTime, mailTextPic;

        public MailViewHolder(View v) {
            super(v);
            mailLayout = (LinearLayout) v.findViewById(R.id.list_layout_mail);
            mailPict = (ImageView) v.findViewById(R.id.list_iv_pict);
            mailStar = (ImageView) v.findViewById(R.id.list_iv_star);
            mailSender = (TextView) v.findViewById(R.id.list_tv_sender);
            mailSubject = (TextView) v.findViewById(R.id.list_tv_subject);
            mailContent = (TextView) v.findViewById(R.id.list_tv_content);
            mailTime = (TextView) v.findViewById(R.id.list_tv_time);
            mailTextPic = (TextView) v.findViewById(R.id.list_tv_textpic);
        }
    }

    public MailAdapter(List<MailModel> mails, Context mailContext) {
        this.mails = mails;
        this.mailContext = mailContext;
    }

    @Override
    public MailAdapter.MailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_mail_item, parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MailViewHolder holder, final int position) {
        MailModel mail = mails.get(position);
        holder.mailSender.setText(mail.getFrom());
        holder.mailSubject.setText(mail.getSubject());
        holder.mailContent.setText(mail.getMessage());
        holder.mailTime.setText(mail.getTimestamp());
        if (TextUtils.isEmpty(mail.getPicture())) {
            holder.mailTextPic.setText(mail.getFrom().substring(0,1));
        } else {
            Picasso.with(mailContext).load(mail.getPicture()).into(holder.mailPict);
            holder.mailTextPic.setVisibility(View.GONE);
        }
        if (!mail.getIsImportant()) {
            holder.mailStar.setImageResource(android.R.drawable.star_off);
        } else {
            holder.mailStar.setImageResource(android.R.drawable.star_on);
        }
        if (mail.getIsRead()) {
            holder.mailSender.setTypeface(null, Typeface.NORMAL);
            holder.mailSubject.setTypeface(null, Typeface.NORMAL);
        } else {
            holder.mailSender.setTypeface(null, Typeface.BOLD);
            holder.mailSubject.setTypeface(null, Typeface.BOLD);
        }
    }
    @Override
    public int getItemCount() {
        return mails.size();
    }
}
