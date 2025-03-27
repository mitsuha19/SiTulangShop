package del.ifs22036.situlangshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import del.ifs22036.situlangshop.data.Item
import del.ifs22036.situlangshop.data.producs
import del.ifs22036.situlangshop.ui.theme.SiTulangShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SiTulangShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SiTulangApp()
                }
            }
        }
    }
}

@Composable
fun SiTulangApp(){
    Scaffold (
        topBar = {
            SiTulangTopAppBar()
        }
    ){ it ->
        LazyColumn (contentPadding = it){
            items(producs){
                ProdukItem(item = it, modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
            }
        }
    }

}


@Composable
fun ProducIcon(
    @DrawableRes producIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
        painter = painterResource(producIcon),
        contentDescription = null
    )
}

@Composable
fun ProducInformation(
    @StringRes producPrice: Int,
    @StringRes producName: Int,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Text(
            text = stringResource(producName),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = stringResource(producPrice)
        )
    }
}

@Composable
private fun ProducItemButton(
    expanded: Boolean,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier){

  IconButton(
      onClick = onClick,
      modifier = modifier
  ) {
      Icon(
          imageVector = if(expanded) Icons.Filled.ExpandLess else
              Icons.Filled.ExpandMore,
          contentDescription = stringResource(R.string.expand_button_content_description),
          tint = MaterialTheme.colorScheme.secondary
      )
  }

}

@Composable
fun ProdukItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false)  }
    val color by animateColorAsState(
        targetValue = if (expanded)
            MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = ""
    )
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
                .background(color = color)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))){
                ProducIcon(item.ImageResourceId)
                ProducInformation(item.price,item.name)
                Spacer(modifier = Modifier.weight(1f))
                ProducItemButton(
                    expanded = expanded,
                    onClick = {expanded = !expanded}
                )

            }
            if (expanded){
                ProducDesc(
                    item.desc,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }


    }
}

@Composable
fun ProducDesc(
    @StringRes itemDesc: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Desc:",
            style = MaterialTheme.typography.labelSmall

        )
        Text(
            text = stringResource(itemDesc),
            style = MaterialTheme.typography.bodyLarge
        )
        var message by remember { mutableStateOf("") }
        Button(onClick = {message = "Item telah dibeli"  }) {
            Text(text = "Buy Now")
        }
        if (message.isNotEmpty()) {
            Text(text = message, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiTulangTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title =
        {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logos),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name)
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun SiTulangPreview(){
    SiTulangShopTheme(darkTheme = false) {
        SiTulangApp()
    }
}

@Preview
@Composable
fun SiTulangDarkPreview(){
    SiTulangShopTheme(darkTheme = true) {
        SiTulangApp()
    }
}

