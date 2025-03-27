package del.ifs22036.situlangshop.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import del.ifs22036.situlangshop.R

data class Item(
    @DrawableRes val ImageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val price: Int,
    @StringRes val desc: Int
)

val producs = listOf(
    Item(R.drawable.produk1, R.string.item_1, R.string.price_1, R.string.desc_1),
    Item(R.drawable.produk2, R.string.item_2, R.string.price_2, R.string.desc_2),
    Item(R.drawable.produk3, R.string.item_3, R.string.price_3, R.string.desc_3),
    Item(R.drawable.produk4, R.string.item_4, R.string.price_4, R.string.desc_4),
    Item(R.drawable.produk5, R.string.item_5, R.string.price_5, R.string.desc_5),
    Item(R.drawable.produk6, R.string.item_6, R.string.price_6, R.string.desc_6),
    Item(R.drawable.produk7, R.string.item_7, R.string.price_7, R.string.desc_7),
    Item(R.drawable.produk8, R.string.item_8, R.string.price_8, R.string.desc_8),
    Item(R.drawable.produk9, R.string.item_9, R.string.price_9, R.string.desc_9),
    Item(R.drawable.produk10, R.string.item_10, R.string.price_10, R.string.desc_10),
)