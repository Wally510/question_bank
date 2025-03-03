<template>
	<view>
		<view style="margin-top: 15px; margin-left: 5%; width: 90%; height:100px;border-radius: 10px; box-shadow:0 0 10px 1px #d9d9d9;" v-for="item in shijuan" @click="goshijuan(item.test_paper_id)">
			<view style="display: flex; position: relative;top: 15px; margin-left: 5%; width: 90%; height: 20px; font-size: 10px;">
				<view style="padding: 0 5px 0 5px; border: 1px solid #00aa00; color: #00aa00; border-radius: 5px;">{{item.data}}</view>
				<view style="padding: 0 5px 0 5px; border: 1px solid #00aa00; color: #00aa00; border-radius: 5px; margin-left: 2%;">{{item.subject_name}}</view>
				<view style="padding: 0 5px 0 5px; border: 1px solid #00aa00; color: #00aa00; border-radius: 5px; margin-left: 2%;">邬仪林</view>
			</view>
			<view style="position: relative;top: 20px; margin-left: 5%; width: 90%; height: 40px; font-size: 20px;">{{item.test_paper_name}}</view>
		</view>
		<view style="z-index: 50; display: flex; background-color: #ffffff; position: fixed; bottom: 0px;width: 100%; height: 60px; border-top: 1px solid #7d7d7d;">
			<button style="background-color: #d66b00; " class="zujuan_button" @click="deletejuan">删除试卷</button>
			<button style="background-color: #00aaff; " class="zujuan_button" @click="gozujuan">添加试卷</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				shijuan:[],
			}
		},
		onLoad() {
			// 获取数据库数据
			uni.request({  
			    url: 'http://localhost:8080/main.java.com.atheism.paper', // 请求的URL  
			    method: 'GET', // 请求方法GET  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.shijuan = res.data;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
		},
		methods: {
			goshijuan(paper_id){
				console.log("试卷");
				uni.navigateTo({
					url:"/pages/paper/test_paper?paper_id="+paper_id,
				});
			},
			gozujuan(){
				console.log("组卷");
				uni.navigateTo({
					url:"/pages/paper/generate_paper"
				});
			}
		}
	}
</script>

<style>
	.zujuan_button{
		position: relative;
		top: 10px; 
		line-height: 40px; 
		color: #ffffff;  
		/* border-radius: 30px; */
		width: 45%; 
		height: 40px;
	}
</style>