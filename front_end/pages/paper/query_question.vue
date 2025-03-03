<template>
	<view class="content">
		<view style="top: 45px; width: 100%; height: 40px; display: flex;">
			<view style="display: flex;position: absolute;left: 5%;top: 5px; border-radius: 10px; width: 90%; height: 30px; background-color: #e7e7e7;">
				<view style="width: 40px;"><image style="width: 30px; height: 30px;" src="../../static/tabs/搜索.png"></image></view>
				<view>
					<input type="text" @input="oninput" v-model="inputxueke" style="height: 30px;" placeholder="请输入学科"/>
				</view>
			</view>
		</view>
		<scroll-view style="flex-wrap: wrap; position: absolute; top: 7%; width: 100%; height: 93%;" scroll-y="true">
			<view style="border-radius: 10%; display:inline-block; margin-top: 10px; margin-left: 8%; width: 38%; height: 150px; text-align: center; line-height: 170px; font-size: 20px; background-image: url('../../static/tabs/学科.png'); background-size: cover; background-position: center;" v-for="(item,index) in xuekesousuo":key="index" @click="godiscipline(item)">
				{{item.name}}
			</view>
		</scroll-view>
		<!-- <view class="discipline" v-for="item in xuekesousuo" @click="godiscipline" style="display: flex; -webkit-justify-content: space-between; border: 1px solid black; height: 50px;">
			<view style="position: relative;left: 10px; line-height: 50px; font-size: 20px;">{{item}}</view>
			<view class="uni-navigate-icon uni-icon">&#xe470;</view>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imageURL:'../../static/tabs/学科.png',
				inputxueke:'',
				xuekesousuo:[],
				xueke:[],
			}
		},
		onLoad() {
			// 获取数据库数据
			uni.request({  
			    url: 'http://localhost:8080/main.java.com.atheism.subject', // 请求的URL  
			    method: 'GET', // 请求方法GET  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.xueke = res.data;
					this.xuekesousuo=this.xueke;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
		},
		methods: {
			oninput(e){
				this.inputxueke = e.target.value;
				this.xuekesousuo=[];
				for(let i=0;i<this.xueke.length;i++){
					var k=0;
					for(let j=0;j<this.inputxueke.length;j++){
						if(this.xueke[i].indexOf(this.inputxueke[j]) == -1){
							k=1;
						}
					}
					if(k==0){
						this.xuekesousuo.push(this.xueke[i]);
					}
				}
			},
			godiscipline(item){
				console.log("学科");
				uni.navigateTo({
					url:"/pages/discipline/discipline?name="+item.name+"&id="+item.id,
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	.uni-navigate-icon {
	    float: right;
	    color: #999999;
	    font-size: 25px;
	    font-weight: normal;
		line-height: 50px;
	}
	.uni-icon {
	    font-family: uniicons;
	    font-weight: normal;
	}
</style>
