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
import com.fullbust.orphy.ui.theme.*
import com.fullbust.orphy.Banniere


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrphyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = background_beige
                    ) {
                        Banniere()
                    }
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.jean_soma),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun InputExample() {
    var currentInput by remember { mutableStateOf("") }
    val itemList = remember { mutableStateListOf<String>() }
    var mess ="";

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = currentInput,
            onValueChange = { currentInput = it },
            label = { Text("Entrez un élément") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (currentInput.isNotBlank()) {
                        mess=currentInput;
                        currentInput = ""  // Réinitialise le champ
                    }
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Liste des éléments :", style = MaterialTheme.typography.titleMedium)
        itemList.forEach { item ->
            Text("• $item")
        }
    }
}

val SomaMessages = listOf(
    Message("Soma", "Salut ! Tu es dispo pour discuter ?"),
    Message("Soma", "Je voulais te montrer quelque chose. Quelque chose de pitoyable"),
    Message("Soma", "Regarde ce que j’ai trouvé hier ! Tes notes de DE HAHA"),
    Message("Soma", "On pourrait peut-être s'en inspirer. Si on veux rater son année et finir avec des dettes colosales"),
    Message("Soma", "Dis-moi ce que tu en penses 😊")
)

@Preview
@Composable
fun PreviewConversation() {
    OrphyTheme {
        Conversation(SomaMessages)
        InputExample()
    }
}

@Preview(name = "Dark Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Lexi", "Hey, you will fail all your DE, it's fantastic!")
    )
}