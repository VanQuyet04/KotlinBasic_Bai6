package com.example.kotlinbasic_bai6.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListScreen : ComponentActivity() {

    @Inject
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val users by viewModel.users.observeAsState(initial = emptyList())

                if (users.isEmpty()) {
                    Text(text = "Loading...")
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "User list",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        UserList(users)
                    }
                }
            }
        }
    }
}

@Composable
fun UserList(users: List<ApiUser>) {
    LazyColumn {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: ApiUser) {
    Row(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = user.picture.large,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
                .clip(MaterialTheme.shapes.medium)
            ,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Name: ${user.name.title} ${user.name.first} ${user.name.last}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Email:" + user.email, style = MaterialTheme.typography.titleMedium)
            Text(text = "Gender: " + user.gender, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Street: " + user.location.street.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
