<template>
    <div class="assemblyMain">
        <div v-for="rowItem in datas" class="assemblyRow">
            <draggable v-for="item in rowItem" :list="rowItem" :key="item.label" :options="{fallbackOnBody: true}"
                       :group="{ name: 'assembly', pull: 'clone', put: false }">
                <mu-paper :z-depth="3" class="assemblyCell">
                    {{item.label}}
                </mu-paper>
            </draggable>
        </div>
        <div class="assemblyRow" style="height: 3px;margin-top: 0px"></div>
    </div>
</template>

<script>
    import draggable from 'vuedraggable'

    export default {
        name: 'AssemblyItem',
        componentName: 'AssemblyItem',
        components: {
            draggable
        },
        props: {
            datas: Array
        },
        methods: {

        }
    };
</script>

<style scoped>
    .assemblyMain {
        border-width: 1px 0 0 0;
        border-style: solid;
        border-color: #AAAAAA;
    }
    .assemblyRow {
        display: flex;
        background: #e9e7ee;
        margin-top: 5px;
    }

    .assemblyCell {
        width: 60px;
        height: 60px;
        margin: 2px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>



















