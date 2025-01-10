package com.verticalcoding.dogs.ui.dogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.verticalcoding.dogs.data.models.Dog

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    viewModel: DogsViewModel = hiltViewModel(),
    navigationController: NavController
) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    if (items is UiState.Success) {
        DogsTypeScreen(
            items = (items as UiState.Success).data,
            onSave = viewModel::addDog,
            onTrash = viewModel::removeDog,
            onRowClick = { navigationController.navigate("details") },
            modifier = modifier
        )
    }
}

@Composable
internal fun DogsTypeScreen(
    items: List<Dog>,
    onSave: (name: String) -> Unit,
    onTrash: (id: Int) -> Unit,
    onRowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var dog by remember { mutableStateOf("Czarek") }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = dog,
                onValueChange = { dog = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(dog) }) {
                Text("Save")
            }
        }

        items.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.clickable {
                    onRowClick()
                }
            ) {
                Text("🐕", modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush = mainGradient())
                    .padding(8.dp)
                )

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(it.name)
                }

                Spacer(Modifier.weight(1f))

                IconButton(onClick = { onTrash(it.id) }
                ) {
                    Icon(
                        Icons.Rounded.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(mainGradient(), blendMode = BlendMode.SrcAtop)
                                }
                            },
                    )
                }

                IconButton(onClick = { onTrash(it.id) }) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private fun mainGradient(): Brush {
    return Brush.linearGradient(
        colors = listOf(Color(0xFF65558F), Color(0xFFEEB6E8))
    )
}