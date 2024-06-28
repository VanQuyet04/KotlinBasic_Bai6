package com.example.kotlinbasic_bai6.ui.screen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.ui.theme.KotlinBasic_Bai6Theme
import com.example.kotlinbasic_bai6.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinBasic_Bai6Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    UserList(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun UserList(viewModel: MainViewModel) {
    val users by viewModel.users.observeAsState(initial = emptyList())
    if(users.isEmpty()){
        Log.d("TAG", "UserList: $users")
    }
    LazyColumn {
        items(users) { user ->
            UserItem(user)
        }
    }
}


@Composable
fun UserItem(user: ApiUser) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberImagePainter(user.picture.large),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "${user.name.first} ${user.name.last}", style = MaterialTheme.typography.titleSmall)
            Text(text = user.email, style = MaterialTheme.typography.titleMedium)
        }
    }
}
