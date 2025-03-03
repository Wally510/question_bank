<template>
	<view>
		<view style="display: flex; -webkit-justify-content: flex-end;justify-content: flex-end;">
			<view style="position: relative; right: 5%;" @click="goteacher">教师登录</view>
		</view>
		<view style="font-size: 30px; position: relative; top: 60px; left: 5%;">你好，请登录</view>
		<view style=" width: 80%; position: relative; left: 10%; top: 100px;">
			<form @submit="formSubmit" @reset="formReset">
				<view style="height: 80px;">
					<input style="border-bottom: 1px solid #d0d0d0; width: 300px; height: 40px; font-size: 20px;" name="nickname" placeholder="请输入学号" />
				</view>
				<view style="height: 100px;">
					<input style="border-bottom: 1px solid #d0d0d0; width: 300px; height: 40px; font-size: 20px;" placeholder="请输入密码" :password="showPassword" />
					<view>(初始密码为123456)</view>
				</view>
				<view style="height: 40px;">
					<checkbox value="agree" /><text style="font-size: 1px;">已阅读并同意<text @click="goxieyi">《用户服务协议》</text>和<text @click="gozhengce">《登录政策》</text></text>
				</view>
				<view class="uni-btn-v">
					<button style="color: #ffffff; border: 1px solid #00aaff; background-color: #00aaff; border-radius: 30px; width: 300px; height: 50px;" form-type="submit">登录</button>
				</view>
				<view style="display: flex; -webkit-justify-content: space-between;">
					<text>忘记密码?</text>
					<text>管理员登录-></text>
				</view>
			</form>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			onBackPress(options) {
				let self = this;
				if (options.from === 'navigateBack') {
					return false;
				}
				console.log("我的");
				uni.switchTab({
					url: '/pages/personal/personal'
				})
				return true;
			},
			goteacher(){
				console.log("教师登录");
				uni.navigateTo({
					url:"/pages/personal/teacher"
				});
			},
			goxieyi(){
				console.log("《用户服务协议》");
				uni.navigateTo({
					url:"/pages/personal/xieyi"
				});
			},
			gozhengce(){
				console.log("《登录政策》");
				uni.navigateTo({
					url:"/pages/personal/zhengce"
				});
			}
		}
	}
</script>

<style>
</style>