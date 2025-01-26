package org.redev.rx.app.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel
import org.redev.rx.app.features.home.domain.viewModel.HomeViewModel

@Composable
fun HomeView(viewModel: HomeViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
        viewModel.fetchDetail()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Body()

        ///nav
        NavBox(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun NavBox(modifier: Modifier) {
    Surface(
        modifier = modifier
            .size(width = 200.dp, height = 70.dp)
            .padding(bottom = 20.dp)
            .padding()
            .shadow(
                elevation = 8.dp,
                spotColor = Color.Gray.copy(.8f),
                ambientColor = Color.Gray.copy(alpha = .23f),
                shape = RoundedCornerShape(40.dp),
            )
            .clip(RoundedCornerShape(40.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Card(
                modifier = Modifier
                    .size(40.dp),
                CircleShape,
                backgroundColor = Color(0xfff97e1f),
            ) {
                Icon(
                    Icons.Default.Home, null,
                    tint = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Icon(
                Icons.Default.Favorite, null,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                Icons.Default.DateRange, null,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                Icons.Default.Person, null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 18.dp)
    ) {
        /// search box
        SearchBox()

        ///categories
        CategoriesBox()

        CardDetail()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardDetail(viewModel: HomeViewModel = koinViewModel()) {
    val pets by viewModel.pets.collectAsStateWithLifecycle()

    val state = rememberCarouselState(itemCount = { pets.size })


    HorizontalUncontainedCarousel(
        state = state,
        itemWidth = 300.dp,
        itemSpacing = 16.dp,
        contentPadding = PaddingValues(start = 19.dp),
        modifier = Modifier.padding(top = 32.dp)
    ) {
        val pet = pets[it]

        Surface(
            modifier =
                Modifier
                    .width(310.dp)
                    .padding(12.dp)
                    .shadow(
                        elevation = 6.dp,
                        ambientColor = Color.Gray.copy(alpha = .38f),
                        spotColor = Color.Gray.copy(alpha = .4f),
                        shape = RoundedCornerShape(20.dp),
                    )
                    .clip(RoundedCornerShape(20.dp)),
            color = Color.White,
        ) {
            Column {
                Surface(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(340.dp)
                            .padding(16.dp),
                    color = Color(
                        pet.color.removePrefix("0x")
                            .toLong(16)
                            .toInt()
                    ).copy(alpha = .23f),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    SubcomposeAsyncImage(
                        model = pet.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(12.dp),
                    )
                }

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column {
                        Text(
                            pet.title,
                            style = MaterialTheme.typography.body2
                                .copy(color = Color.Gray)
                        )
                        Text(
                            pet.name,
                            style = MaterialTheme.typography.body1
                                .copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Surface(
                        modifier = Modifier.size(width = 30.dp, height = 30.dp),
                        shape = CircleShape,
                        color = Color(0xfff97e1f),
                    ) {
                        Icon(
                            Icons.Default.Search, null,
                            modifier = Modifier.padding(4.dp),
                            tint = Color.White,
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        pet.localtion,
                        style = MaterialTheme.typography
                            .caption.copy(color = Color.Gray)
                    )
                    Text(
                        pet.age, style = MaterialTheme.typography
                            .caption.copy(color = Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesBox(viewModel: HomeViewModel = koinViewModel()) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()


    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Categories",
            style = MaterialTheme.typography.h6
        )

        ///list category
        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(100.dp),
        ) {
            items(categories, key = { it.id }) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    Arrangement.Center,
                    Alignment.CenterHorizontally,
                ) {
                    Surface(
                        modifier = Modifier
                            .size(60.dp),
                        color = Color(
                            it.color
                                .removePrefix("0x")
                                .toLong(16)
                                .toInt()
                        )
                            .copy(alpha = .14f),
                        shape = CircleShape,
                    ) {
                        SubcomposeAsyncImage(
                            model = it.image,
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(12.dp),
                        )
                    }


                    Text(it.name)
                }
            }
        }
    }
}

@Composable
private fun SearchBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.Gray.copy(alpha = .3f), RoundedCornerShape(40.dp))
                .padding(horizontal = 8.dp),
            color = Color.Transparent,
        ) {
            Row(
                Modifier.padding(6.dp),
                Arrangement.Start,
                Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search, null,
                    modifier = Modifier.padding(end = 8.dp),
                    tint = Color.Gray.copy(alpha = .3f),
                )

                BasicTextField(
                    "", onValueChange = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                )
            }
        }
        Spacer(modifier = Modifier.width(6.dp))

        ///buttom setting
        Button(
            onClick = {},
            modifier = Modifier
                .size(42.dp),
            colors = ButtonDefaults.buttonColors(Color(0xfff97e1f)),
            shape = CircleShape,
        ) {

            Image(
                Settings,
                null,
                modifier = Modifier
                    .size(18.dp),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}


private val Settings: ImageVector
    get() {
        if (_Settings != null) {
            return _Settings!!
        }
        _Settings = ImageVector.Builder(
            name = "Settings",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(6f, 9.5f)
                curveTo(6.9319f, 9.5f, 7.715f, 10.1374f, 7.937f, 11f)
                horizontalLineTo(13.5f)
                curveTo(13.7761f, 11f, 14f, 11.2239f, 14f, 11.5f)
                curveTo(14f, 11.7455f, 13.8231f, 11.9496f, 13.5899f, 11.9919f)
                lineTo(13.5f, 12f)
                lineTo(7.93673f, 12.001f)
                curveTo(7.7143f, 12.8631f, 6.9315f, 13.5f, 6f, 13.5f)
                curveTo(5.0685f, 13.5f, 4.2857f, 12.8631f, 4.0633f, 12.001f)
                lineTo(2.5f, 12f)
                curveTo(2.2239f, 12f, 2f, 11.7761f, 2f, 11.5f)
                curveTo(2f, 11.2545f, 2.1769f, 11.0504f, 2.4101f, 11.0081f)
                lineTo(2.5f, 11f)
                horizontalLineTo(4.06301f)
                curveTo(4.285f, 10.1374f, 5.0681f, 9.5f, 6f, 9.5f)
                close()
                moveTo(6f, 10.5f)
                curveTo(5.4477f, 10.5f, 5f, 10.9477f, 5f, 11.5f)
                curveTo(5f, 12.0523f, 5.4477f, 12.5f, 6f, 12.5f)
                curveTo(6.5523f, 12.5f, 7f, 12.0523f, 7f, 11.5f)
                curveTo(7f, 10.9477f, 6.5523f, 10.5f, 6f, 10.5f)
                close()
                moveTo(10f, 2.5f)
                curveTo(10.9319f, 2.5f, 11.715f, 3.1374f, 11.937f, 4f)
                lineTo(13.5f, 4f)
                curveTo(13.7761f, 4f, 14f, 4.2239f, 14f, 4.5f)
                curveTo(14f, 4.7455f, 13.8231f, 4.9496f, 13.5899f, 4.9919f)
                lineTo(13.5f, 5f)
                lineTo(11.9367f, 5.00102f)
                curveTo(11.7144f, 5.8631f, 10.9316f, 6.5f, 10f, 6.5f)
                curveTo(9.0685f, 6.5f, 8.2857f, 5.8631f, 8.0633f, 5.001f)
                lineTo(2.5f, 5f)
                curveTo(2.2239f, 5f, 2f, 4.7761f, 2f, 4.5f)
                curveTo(2f, 4.2545f, 2.1769f, 4.0504f, 2.4101f, 4.0081f)
                lineTo(2.5f, 4f)
                lineTo(8.06301f, 3.99998f)
                curveTo(8.285f, 3.1374f, 9.0681f, 2.5f, 10f, 2.5f)
                close()
                moveTo(10f, 3.5f)
                curveTo(9.4477f, 3.5f, 9f, 3.9477f, 9f, 4.5f)
                curveTo(9f, 5.0523f, 9.4477f, 5.5f, 10f, 5.5f)
                curveTo(10.5523f, 5.5f, 11f, 5.0523f, 11f, 4.5f)
                curveTo(11f, 3.9477f, 10.5523f, 3.5f, 10f, 3.5f)
                close()
            }
        }.build()
        return _Settings!!
    }

private var _Settings: ImageVector? = null
