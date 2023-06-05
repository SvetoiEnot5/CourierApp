package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //курьер
    Courier courier = new Courier("Токарев Александр", "2803");

    //посылки
    SmallPack Computer = new SmallPack("15,6", true, "Севастополь", "Москва");
    SmallPack meal = new SmallPack("маленький", false, "Стамбул", "Рейкъявик");
    Documents docs = new Documents("Москва", "Владивосток");
    BigPack maleta = new BigPack("большой", false, 20, "Барселона", "Питер");
    Documents docs2 = new Documents("Сидней", "Вашингтон");

    //компании
    Company ozon = new Company("Ozon", "456");
    Company sber = new Company("Сбер", "2313");
    Company alfa = new Company("Альфа", "23");

    //заказы
    Order order1 = new Order(ozon, Computer, Computer.getFrom(), Computer.getTo(), 300);
    Order order2 = new Order(ozon, meal, meal.getFrom(), meal.getTo(), 1111);
    Order order3 = new Order(sber, docs, docs.getFrom(), docs.getTo(), 2100);
    Order order4 = new Order(alfa, maleta, maleta.getFrom(), maleta.getTo(), 1700);
    Order order5 = new Order(sber, docs2, docs2.getFrom(), docs2.getTo(), 4000);

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView courier_name = findViewById(R.id.courier_name);
        courier_name.setText(courier.getFIO());

        courier.setFeatures("Автомобиль");

        TextView features = findViewById(R.id.couruier_features);
        features.setText(courier.getFeatures());

        courier.addOrder(order1);
        courier.addOrder(order2);
        courier.addOrder(order3);
        courier.addOrder(order4);
        courier.addOrder(order5);


        listView = findViewById(R.id.listView);

        OrderAdapter adapter = new OrderAdapter(this, courier.getOrders());

        listView.setAdapter(adapter);

        Button btn_ok = findViewById(R.id.button_ok);
        Button btn_clear = findViewById(R.id.button_clear);
        Button btn_firm_sort = findViewById(R.id.firmSort);
        Button btn_type_sort = findViewById(R.id.typeSort);
        Button btn_cost_sort = findViewById(R.id.costSort);

        btn_ok.setOnClickListener(view -> {
            double result = 0;
            ArrayList<Order> ordersToRemove = new ArrayList<>();
            for (int i = 0; i < adapter.getOrders().size(); i++) {
                if (adapter.getOrders().get(i).isSelected()) {
                    result += Double.parseDouble(adapter.getOrders().get(i).getCost());
                    ordersToRemove.add(adapter.getOrders().get(i));
                }
            }
            showInfo(result, ordersToRemove);
        });

        btn_cost_sort.setOnClickListener(view ->{
            Collections.sort(courier.getOrders(), new SortByCost());
            OrderAdapter adapter1 = new OrderAdapter(this, courier.getOrders());
            listView.setAdapter(adapter1);
        });

        btn_firm_sort.setOnClickListener(view ->{
            Collections.sort(courier.getOrders(), new SortByFirm());
            OrderAdapter adapter2 = new OrderAdapter(this, courier.getOrders());
            listView.setAdapter(adapter2);
        });

        btn_type_sort.setOnClickListener(view ->{
            Collections.sort(courier.getOrders(), new SortByType());
            OrderAdapter adapter3 = new OrderAdapter(this, courier.getOrders());
            listView.setAdapter(adapter3);
        });

        btn_clear.setOnClickListener(view -> {

            for (int i = 0; i < adapter.getOrders().size(); i++) {
                adapter.getOrders().get(i).setSelected(false);
            }
            adapter.notifyDataSetChanged();

        });
    }

    private void showInfo(double cost, ArrayList<Order> orders) {
        Toast.makeText(this, "Общая стоимость: " + cost, Toast.LENGTH_LONG).show();
        for (int i = 0; i < orders.size(); i++){
            courier.getOrders().remove(orders.get(i));
        }
        OrderAdapter adapter = new OrderAdapter(this, courier.getOrders());
        listView.setAdapter(adapter);
    }

}

// ряд кнопок, фильтрация по стоимости, по названию, компаратор, сортировка по каждому кроме чекбокс, при взятии заказа он удаляется, не удалять и перерисовывать