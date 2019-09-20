<template>
    <draggable :class="['full-screen', 'draggable-main']" :list="elements" v-model="elements" group="assembly">
        <assembly-factory :assemblyClass="chooseIndex == index?'element-choose':''"
                          v-for="(item,index) in elements"
                          :key="item.id + '-' + index"
                          :assemblyData="item"
                          @choose="editInfo(index,item)"/>
    </draggable>
    <div :class="assemblyClass" v-html="showHtml" @click="choose">

    </div>
</template>

<script>
    export default {
        name: 'AssemblyFactory',
        componentName: 'AssemblyFactory',
        data: function () {
            return {
                styleMap:{
                    width:{
                        name: "width",
                        suffix: "px"
                    },
                    height:{
                        name: "height",
                        suffix: "px"
                    },
                    flex:{
                        name: "flex-direction"
                    },
                    justify:{
                        name: "justify-content"
                    },
                    align:{
                        name: "align-items"
                    }
                }
            }
        },
        computed:{
            showHtml(){
                return this.createHtml();
            }
        },
        props: {
            assemblyData: Object,
            assemblyClass: String
        },
        watch:{
            assemblyClass(){
                console.log(12345)
            }
        },
        methods:{
            createHtml(){
                this.assemblyData;
                let html = "<%tage %style %props %event></%tage>";
                html = html.replace(/\%tage/g, this.assemblyData.tage);
                html = html.replace(/\%style/g, this.createStyle());
                html = html.replace(/\%props/g, this.createProps());
                html = html.replace(/\%event/g, this.createEvent());
                return html;
            },
            createStyle(){
                let styleStr = "";
                for (let key in this.assemblyData.attr.style) {
                    let styleInfo = this.styleMap[key];
                    if(styleInfo){
                        styleStr += (styleInfo.name + ": " + this.assemblyData.attr.style[key] + (styleInfo.suffix?styleInfo.suffix:"") + ";")
                    }else{
                        styleStr += this.assemblyData.attr.style[key];
                    }
                }
                return "style='" + styleStr + "'";
            },
            createProps(){
                return ""
            },
            createEvent(){
                return ""
            },
            choose(){
                this.$emit('choose');
            }
        }
    };
</script>

<style scoped>

</style>



















