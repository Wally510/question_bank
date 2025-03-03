<template>
	<view>
		<view style="display: flex; border: 1px solid black; height: 80px;" @click="goLogon">
			<view style="position: relative;top: 10px; margin-left: 10px; border: 1px solid black; width: 60px; height: 60px; border-radius: 50%;"></view>
			<view style="line-height: 80px; margin-left: 10px; font-size: 25px;">{{xingming}}</view>
		</view>
		<view style="position: relative; top: 50px; border-bottom: 1px solid #c8c8c8;">
			<view class="discipline" @click="godiscipline" style="display: flex; -webkit-justify-content: space-between; border-top: 1px solid #c8c8c8; height: 50px;">
				<view style="position: relative;left: 10px; line-height: 50px; font-size: 20px;">我的收藏</view>
				<view class="uni-navigate-icon uni-icon">&#xe470;</view>
			</view>
			<view class="discipline" @click="godiscipline" style="display: flex; -webkit-justify-content: space-between; border-top: 1px solid #c8c8c8; height: 50px;">
				<view style="position: relative;left: 10px; line-height: 50px; font-size: 20px;">消息</view>
				<view class="uni-navigate-icon uni-icon">&#xe470;</view>
			</view>
			<view class="discipline" @click="godiscipline" style="display: flex; -webkit-justify-content: space-between; border-top: 1px solid #c8c8c8; height: 50px;">
				<view style="position: relative;left: 10px; line-height: 50px; font-size: 20px;">设置</view>
				<view class="uni-navigate-icon uni-icon">&#xe470;</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				xingming:'请登录',
			}
		},
		methods: {
			goLogon(){
				if(this.xingming=='请登录'){
					console.log("登录");
					uni.navigateTo({
						url:"/pages/personal/Longon"
					});
				}else{
					console.log(this.xingming);
					uni.navigateTo({
						url:"/pages/personal/information"
					});
				}
			}
		}
	}
</script>

<style>
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	.uni-navigate-icon {
	    float: right;
	    color: #999999;
	    font-size: 20px;
	    font-weight: normal;
		line-height: 50px;
	}
	.uni-icon {
	    font-family: uniicons;
	    font-weight: normal;
	}
</style>