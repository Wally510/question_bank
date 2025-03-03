<template>
  <div>
    <div :class="showTotal ? 'total-introduce' : 'detailed-introduce'">
      <div class="intro-content" :aaa="intext" ref="desc">
        <span class="merchant-desc" v-if="richText">
		  <rich-text :nodes="richText"></rich-text>
        </span>
        <div class="unfold" @click="showTotalIntro" v-if="showExchangeButton">
          <p>{{ exchangeButton ? '展开' : '收起' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	import 'katex/dist/katex.min.css';
	import katex from 'katex';
export default {
  name: 'Spread',
  data() {
    return {
	  intext:'高斯是德国著名的数学家，近代数学奠基者之一，享有“数学王子”的称号. 设$x \\in R$，用$$表示不超过$[x]$的最大整数，$y=[x]$也被称为“高斯函数”，例如：$[-3.5]=-4$, $[2.1]=2$. 已知函数$f(x)=[x+1]-x$，下列说法正确的是 ( ) .',
	  richText:'函数$y = \\frac{3x + 4}{x + 2} (x \\le 0)$ 的值域是_____.',
      introduce: '',
      // 是否展示所有文本内容
      showTotal: true,
      // 显示展开还是收起
      exchangeButton: true,
      // 是否显示展开收起按钮
      showExchangeButton: false,
      rem: '',
    };
  },
  mounted() {
    this.init();
  },
  onLoad() {
  	const mathText = '高斯是德国著名的数学家，近代数学奠基者之一，享有“数学王子”的称号. 设$x \\in R$，用$$表示不超过$[x]$的最大整数，$y=[x]$也被称为“高斯函数”，例如：$[-3.5]=-4$, $[2.1]=2$. 已知函数$f(x)=[x+1]-x$，下列说法正确的是 ( ) .';
  	const renderedText = this.renderMathInHtml(mathText);
  	this.richText = renderedText;
  },
  methods: {
	renderMathInHtml(text) {
	    // 使用 KaTeX 渲染数学表达式
	    const renderedText = text.replace(/\$(.*?)\$/g, (match, math) => katex.renderToString(math));
	    return renderedText;
	},
    showTotalIntro() {
      this.showTotal = !this.showTotal;
      this.exchangeButton = !this.exchangeButton;
    },
    getRem() {
      console.log('getRem');
      const defaultRem = 16;
      let winWidth = window.innerWidth;
      console.log('winWidth:' + winWidth);
      let rem = (winWidth / 375) * defaultRem;
      return rem;
    },
    init() {
      this.introduce =
        'Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue是一套用于构建用户界面的渐进式框架。Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。';
    },
  },
  watch: {
    introduce: function () {
      this.$nextTick(
        function () {
          // 判断介绍是否超过四行
          let rem = parseFloat(this.getRem());
          console.log('watch 中的rem', rem);
          if (!this.$refs.desc) {
            console.log('desc null');
            return;
          }
          let descHeight = window.getComputedStyle(this.$refs.desc).height.replace('px', '');
          console.log('descHeight:' + descHeight);
          console.log('如果 descHeight 超过' + 5.25 * rem + '就要显示展开按钮');
          if (descHeight > 5.25 * rem) {
            console.log('超过了四行');
            // 显示展开收起按钮
            this.showExchangeButton = true;
            this.exchangeButton = true;
            // 不是显示所有
            this.showTotal = false;
          } else {
            // 不显示展开收起按钮
            this.showExchangeButton = false;
            // 没有超过四行就显示所有
            this.showTotal = true;
            console.log('showExchangeButton', this.showExchangeButton);
            console.log('showTotal', this.showTotal);
          }
        }.bind(this)
      );
    },
  },
};
</script>

<style lang="less" scoped rel="stylesheet/less">
// p和.merchant-desc是共有样式
p {
    margin: 0;
  line-height: 21px;
  color: #7fbe87;
}
.merchant-desc {
  width: 100%;
  line-height: 21px;
}
// 展示全部的样式
.total-introduce {
  text-align: left;
  height: auto;
  overflow: hidden;
  font-size: 14px;
  color: #434343;
  margin: 10px;
  .unfold {
    display: block;
    z-index: 11;
    float: right;
    width: 40px;
    height: 21px;
  }
}
// 展示部分的样式
.detailed-introduce {
font-size: 14px;
color: #434343;
position: relative;
overflow: hidden;
margin: 10px;
.intro-content {
 // 最大高度设为4倍的行间距
  max-height: 84px;
  line-height: 21px;
  word-wrap: break-word;
  /*强制打散字符*/
  word-break: break-all;
  background: #ffffff;
  /*同背景色*/
  color: #ffffff;
  overflow: hidden;
  // 显示最后一行文字
  &:after,
  // 这是展开前实际显示的内容
  &:before {
  content: attr(aaa);
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  color: #434343
  // overflow: hidden;
  }
  // 把最后最后一行自身的上面三行遮住
  &:before {
  display: block;
  overflow: hidden;
  z-index: 1;
  max-height: 63px;
  background: #ffffff;
  }
  &:after {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  height: 81px;
  /*截取行数*/
  -webkit-line-clamp: 4;
  text-overflow: ellipsis;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  /*行首缩进字符数，值为[(截取行数-1)*尾部留空字符数]*/
  text-indent: -12em;
  /*尾部留空字符数*/
  padding-right: 4em;
  }
  .unfold {
  z-index: 11;
  width: 40px;
  height: 21px;
  outline: 0;
  position: absolute;
  right: 0;
  bottom: 0;
  }
}
}
</style>

