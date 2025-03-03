<template>
	<view>
		<view style="z-index: 50; display: flex; background-color: #ffffff; position: fixed; height: 40px; width: 100%;">
			<view style="width: 50%; text-align: center; line-height: 40px;" :class="{'biaoqian': tabIndex==1}" @click="biao1">
				<text class="biaoqian-zi">学科：{{tabname}}</text>
				<view class="biaoqian-xian"></view>
			</view>
			<view style="width: 50%; text-align: center; line-height: 40px;" :class="{'biaoqian': tabIndex==2}" @click="biao2">
				<text class="biaoqian-zi">收藏</text>
				<view class="biaoqian-xian"></view>
			</view>
		</view>
		<view style="display: flex; height: 3px; width: 100%;">
			<!-- <view style="width: 50%; background-color: #00aaff; border-radius: 5px;"></view> -->
		</view>
		<scroll-view class="uni-container" scroll-y="true">
			<view class="uni-panel" v-for="(item,index) in list" :key="index" @click="gotiku(item.id)">
				<view class="uni-panel-h">
					<text class="uni-panel-text">第{{index + 1}}章 {{item.name}}</text>
					<text class="uni-navigate-icon uni-icon">&#xe470;</text>
				</view>
			</view>
		</scroll-view>
		<view style="z-index: 50; display: flex; background-color: #ffffff; position: fixed; bottom: 0px;width: 100%; height: 60px; border-top: 1px solid #7d7d7d;">
			<button style="background-color: #d66b00; " class="zujuan_button" @click="shanchu">删除题目</button>
			<button style="background-color: #00aaff; " class="zujuan_button" @click="gotianjia">添加题目</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tabid:0,
				tabname:'高等数学',
				tabIndex:1,
				list: []
			}
		},
		onLoad(option) {
			console.log(option.name); 
			this.tabname=option.name;
			this.tabid=option.id;
			uni.request({  
			    url: 'http://localhost:8080/main.java.com.atheism.chapter', // 请求的URL  
			    method: 'POST', // 请求方法POST  
			    data: {  
			        name: this.tabname, // 请求的数据  
			    },  
			    header: {  
			        'content-type': 'application/json' // 设置请求头  
			    },  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.list = res.data;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
		},
		// #ifdef H5
		// #endif
		methods: {
			biao1(){
				this.tabIndex=1;
			},
			biao2(){
				this.tabIndex=2;
			},
			goxiugai(){
				console.log("修改");
				uni.navigateTo({
					url:"/pages/discipline/increase"
				});
			},
			gotianjia(){
				console.log("添加");
				uni.navigateTo({
					url:"/pages/discipline/increase?id="+this.tabid+"&name="+this.tabname
				});
			},
			gotiku(id){
				console.log("题库");
				uni.navigateTo({
					url:"/pages/discipline/question?id="+id,
				});
			}
		}
	}
</script>

<style>
	page{
		background-color: #ffffff;
	}
	.biaoqian .biaoqian-zi{
		color: #00aaff;
	}
	.biaoqian .biaoqian-xian{
		width: 100%; 
		height: 3px; 
		background-color: #00aaff; 
		border-radius: 5px;
	}
	/*.zujuan_button{
		position: relative;
		top: 10px; 
		line-height: 40px; 
		color: #ffffff;  
		background-color: #00aaff; 
		border-radius: 30px; 
		width: 90%; 
		height: 40px;
	} */
	/* #ifndef APP-PLUS-NVUE */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	page {
	    min-height: 100%;
	    height: auto;
	}
	/* #endif */
	
	/* 解决头条小程序字体图标不显示问题，因为头条运行时自动插入了span标签，且有全局字体 */
	/* #ifdef MP-TOUTIAO */
	/* text :not(view) {
	    font-family: uniicons;
	} */
	/* #endif */
	
	.uni-icon {
	    font-family: uniicons;
	    font-weight: normal;
	}
	
	.uni-container {
		padding-top: 40px;
	    padding-bottom: 50px;
	}
	
	.uni-header-logo {
		/* #ifdef H5 */
		display: flex;
		/* #endif */
	    padding: 15px 15px;
	    flex-direction: column;
	    justify-content: center;
	    align-items: center;
	    margin-top: 10rpx;
	}
	
	.uni-header-image {
	    width: 80px;
	    height: 80px;
	}
	
	.uni-hello-text {
	    margin-bottom: 20px;
	}
	
	.hello-text {
	    color: #7A7E83;
	    font-size: 14px;
	    line-height: 20px;
	}
	
	.hello-link {
	    color: #7A7E83;
	    font-size: 14px;
	    line-height: 20px;
	}
	
	.uni-panel {
	    margin-bottom: 15px;
	}
	
	.uni-panel-h {
		/* #ifdef H5 */
		display: flex;
		/* #endif */
	    background-color: #ffffff;
	    flex-direction: row !important;
		/* justify-content: space-between !important; */
	    align-items: center !important;
	    padding: 12px;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}
	/*
	.uni-panel-h:active {
	    background-color: #f8f8f8;
	}
	 */
	
	.uni-panel-text {
	    flex: 1;
	    color: #000000;
	    font-size: 16px;
	    font-weight: normal;
	}
	
	.uni-panel-icon {
	    margin-left: 5px;
	    color: #999999;
	    font-size: 14px;
	    font-weight: normal;
	    transform: rotate(0deg);
	    transition-duration: 0s;
	    transition-property: transform;
	}
	
	.uni-panel-icon-on {
	    transform: rotate(180deg);
	}
	
	.uni-navigate-item {
		/* #ifdef H5 */
		display: flex;
		/* #endif */
	    flex-direction: row;
	    align-items: center;
	    background-color: #FFFFFF;
	    border-top-style: solid;
	    border-top-color: #f0f0f0;
	    border-top-width: 1px;
	    padding: 12px;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}
	
	.uni-navigate-item:active {
	    background-color: #f8f8f8;
	}
	
	.uni-navigate-text {
	    flex: 1;
	    color: #000000;
	    font-size: 14px;
	    font-weight: normal;
	}
	
	.uni-navigate-icon {
	    float: right;
	    color: #999999;
	    font-size: 14px;
	    font-weight: normal;
	}
	.zujuan_button{
		position: relative;
		top: 10px; 
		line-height: 40px; 
		color: #ffffff;  
		/* border-radius: 30px; */
		width: 40%; 
		height: 40px;
	}
</style>