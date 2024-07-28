import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.yuanzix.revitalize.ui.viewmodels.loginViewModel.LoginStates
import dev.yuanzix.revitalize.ui.viewmodels.loginViewModel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onLogin: () -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }
    val dialogTitle = remember { mutableStateOf("") }
    val dialogMessage = remember { mutableStateOf("") }
    val loginState = loginViewModel.loginState

    if (showDialog.value) {
        Dialog(
            title = dialogTitle.value, message = dialogMessage.value, showDialog = showDialog
        )
        loginViewModel.handleState()
    }

    when (loginState) {
        LoginStates.BlankFields -> {
            dialogTitle.value = "Fields are empty"
            dialogMessage.value = "Please enter your username and password"
            showDialog.value = true
        }

        LoginStates.Loading -> {
            // Show loading indicator
        }

        LoginStates.CredentialsIncorrect -> {
            dialogTitle.value = "Invalid Credentials"
            dialogMessage.value =
                "The username or password you entered is incorrect. Please try again."
            showDialog.value = true
        }

        LoginStates.SomethingWentWrong -> {
            dialogTitle.value = "Error"
            dialogMessage.value = "Something went wrong. Please try again later."
            showDialog.value = true
        }

        LoginStates.Success -> {
            // Navigate to HomeScreen
            onLogin()
        }

        LoginStates.Login -> {
            // Do nothing, waiting for user input
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back to\nreVitalized",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = loginViewModel.username,
                onValueChange = { loginViewModel.username = it.uppercase() },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.95f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = loginViewModel.password,
                onValueChange = { loginViewModel.password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.95f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box {
                FloatingActionButton(
                    onClick = { loginViewModel.handleLogin() },
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (loginState == LoginStates.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        Text(
                            "Login",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Dialog(title: String, message: String, showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.W500
                )
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                ElevatedButton(onClick = { showDialog.value = false }) {
                    Text(text = "OK")
                }
            },
        )
    }
}
