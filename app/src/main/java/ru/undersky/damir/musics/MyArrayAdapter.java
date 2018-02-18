package ru.undersky.damir.musics;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Model> {
    private final Activity context;
    private final Model[] names;

    public MyArrayAdapter(Activity context, Model[] names) {
        super(context, R.layout.item_for_list_view, names);
        this.context = context;
        this.names = names;
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    static class ViewHolder {
        public CheckBox checkBox;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder буферизирует оценку различных полей шаблона элемента

        ViewHolder holder;
        // Очищает сущетсвующий шаблон, если параметр задан
        // Работает только если базовый шаблон для всех классов один и тот же
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_for_list_view, null, true);
            holder = new ViewHolder();
            holder.textView = rowView.findViewById(R.id.textView);
            holder.checkBox = rowView.findViewById(R.id.checkBox);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(names[position].getName());
        holder.checkBox.setChecked(names[position].getValue());
        // Изменение иконки для Windows и iPhone
        //String s = names[position].getName();


        return rowView;
    }
}