import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        AppContent(viewModel = HomeViewModel())

    }
}

@Composable
fun AppContent(viewModel: HomeViewModel) {
    val products = viewModel.products.collectAsState()

    BoxWithConstraints {
        val scope = this
        val maxWidth = scope.maxWidth
        var col = 2
        var modifier = Modifier.fillMaxWidth()

        if (maxWidth > 840.dp) {
            col = 3
            modifier = Modifier.widthIn(max = 1080.dp)
        }
        val scrollState = rememberLazyGridState()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(col),
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {

                items(
                    items = products.value,
                    key = { product -> product.id.toString() }) { product ->
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val painter = rememberImagePainter(url = product.image.toString())
                            Image(
                                painter = painter,
                                modifier = Modifier.height(120.dp),
                                contentDescription = product.title
                            )
                            Text(
                                text = product.title.toString(),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                    }

                }

            }
        }

    }
}