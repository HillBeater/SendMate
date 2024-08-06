package com.example.sendmate

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sendmate.ui.theme.GreyColor

data class MateItem(
    val id: Int,
    var name: String,
    var qualification: String,
    var address: String
)

fun createPersonList(): List<MateItem> {
    return listOf(
        MateItem(0, "Anurag Dhiman", "Master of Computer Applications", "Dharamshala"),
        MateItem(1, "Ankit Sharma", "Master of Computer Applications", "Nurpur"),
        MateItem(2, "Abhishek Parmar", "Bachelor of Commerce", "Kangra"),
        MateItem(3, "Arun Mehra", "Bachelor of Computer Applications", "Yamunanagar"),
        MateItem(4, "Prashant Dhiman", "Masters in Business Administration", "Dharamshala"),
        MateItem(5, "Sparsh Jambal", "Bachelor of Legislative Laws", "Nurpur"),
        MateItem(6, "Puja Thakur", "Masters in Business Administration", "Hamirpur"),
        MateItem(7, "Chander Mohan Vardhan", "Bachelor in Business Administration", "Kangra"),
        MateItem(8, "Arpit Dhiman", "BTech ECE", "Dharamshala"),
        MateItem(9, "Abhinandhan Kumar", "BTech CSE", "Dharamshala"),
    )
}


@Composable
fun SendMateApp(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "MateList",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        )  {
            items(createPersonList()) { item ->
                MateListItem(item = item,
                    onShareClick = {
                        sendWhatsAppMessage(context, item)
                    })
            }
        }
    }

}

fun sendWhatsAppMessage(context: Context, mateItem: MateItem) {
    val phoneNumber = "+911234567890" // Replace with the desired phone number
    val message = "Name: ${mateItem.name}\nQualification: ${mateItem.qualification}\nAddress: ${mateItem.address}"
    val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MateListItem(
    item: MateItem,
    onShareClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(
                border = BorderStroke(2.dp, GreyColor),
                shape = RoundedCornerShape(15)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 10.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(GreyColor)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .weight(1f)

        ) {
            Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black )
            Text(text = item.qualification, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
            Text(text = item.address, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
        }

        IconButton(onClick = onShareClick) {
            Icon(imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(30.dp))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}