package com.fullbust.orphy

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.* // image, border, shape
import androidx.compose.foundation.layout.* // size, row, column, padding, width, box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.* // text, button , surface, scaffold, materialTheme
import androidx.compose.runtime.* // remember, mutableStateOf, by, setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.fullbust.orphy.ui.theme.OrphyTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.fullbust.orphy.ui.theme.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember

@Composable
fun Banniere(){
    var shownav by remember { mutableStateOf(false) }
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                background_text_brown,
                                background_beige
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home",
                    color = Color.White,
                    fontSize = 30.sp

                )
            }
        }
    }
    Row{
        Box() {
            IconButton(
                onClick = { shownav = true },
                modifier = Modifier.offset(x = 15.dp, y = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.barres),
                    contentDescription = "Bouton image ",
                    modifier = Modifier.size(150.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = { /* action à exécuter */ },
                modifier = Modifier.offset(x = 340.dp, y = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = "Bouton image",
                    modifier = Modifier.size(150.dp),
                    tint = Color.Unspecified
                )
            }
            if (shownav) {
                navigation( onClose ={ shownav = false})
            }
        }
    }
}


@Composable
fun exitpopup(onClose: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { onClose() },
    )
    {
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center ){
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {},
                colors = CardDefaults.cardColors(
                    containerColor = white
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .background(white),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text="Are you sure that you want to quit this groupchat ?",
                        style = MaterialTheme.typography.titleMedium,
                        color = background_text_brown,
                        textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp) // épaisseur de la ligne
                            .clip(RoundedCornerShape(4.dp)) // bords arrondis
                            .background(Color.Gray)
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                // to complete
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = option_button_grey,
                                contentColor = chat_text_grey
                            )
                        ) {
                            Text(text = "Yes", fontSize = 18.sp)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(onClick = onClose,
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = option_button_grey,
                                contentColor = chat_text_grey
                            )
                        ) {
                            Text(text = "No", fontSize = 18.sp)
                        }
                    }
                }
            }
        }}
}

@Composable
fun navigation(onClose: () -> Unit){
    var showexit by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { onClose() },
    )
    {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(x = 1.dp, y = 50.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {},
            colors = CardDefaults.cardColors(
                containerColor = white)

        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .background(white),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        // to complete
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = option_button_grey,
                        contentColor = chat_text_grey
                    )
                ) {
                    Text(text = "Home", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { showexit = true},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = option_button_grey,
                        contentColor = chat_text_grey
                    )
                ) {
                    Text(text = "Exit", fontSize = 18.sp)
                }
            }
        }
        if(showexit){
            exitpopup(onClose= {showexit= false} )
        }
    }
}

@Preview
@Composable
fun Previewbanniere() {
    Banniere()
}