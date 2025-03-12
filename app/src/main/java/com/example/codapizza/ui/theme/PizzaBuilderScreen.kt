//Sam Lato CSC567 CodaPizza app

package com.example.codapizza.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codapizza.R
import com.example.codapizza.model.Topping
import com.example.codapizza.model.ToppingPlacement
import com.example.codapizza.model.Pizza
import java.text.NumberFormat

@Preview
@Composable
fun PizzaBuilderScreen(modifier: Modifier=Modifier){
    var pizza by rememberSaveable { mutableStateOf(Pizza()) }
    Column(
        modifier = modifier
            //.fillMaxSize()
    ) {
        ToppingsList(
            pizza = pizza,
            onEditPizza = { pizza = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}


/*
private var pizza = Pizza(toppings = mapOf(Topping.Pepperoni to ToppingPlacement.All, Topping.Pineapple to ToppingPlacement.All))
    set (value){
        Log.d("PizzaBuilderScreen", "Reassigned pizza to $value")
        field=value
    }

*/
//private var pizza by mutableStateOf(Pizza())

@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
) {
    //var pizza by remember {mutableStateOf(Pizza())}
    LazyColumn(
        modifier = modifier
    ) {
        items(Topping.values()) {topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    val isOnPizza = pizza.toppings[topping]!=null
                    onEditPizza(pizza.withTopping(
                        topping=topping,
                        placement = if (isOnPizza){
                            null
                        } else {
                            ToppingPlacement.All
                        })
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderButton(
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = { }
    ) {
        val currencyFormatter = remember {
            NumberFormat.getCurrencyInstance()
        }
        //val currencyFormatter= NumberFormat.getCurrencyInstance()
        val price=currencyFormatter.format(pizza.price)
        Text(
            text = stringResource(R.string.place_order_button, price).toUpperCase(Locale.current)
        )
    }
}