<template>
  <div id="app">
    <h1>待办事项</h1>

    <!-- 添加新待办 -->
    <div>
      <input v-model="newTodo" @keyup.enter="addTodo" placeholder="输入新待办...">
      <button @click="addTodo">添加</button>
    </div>

    <!-- 待办列表 -->
    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <input type="checkbox" v-model="todo.completed" @change="updateTodo(todo)">
        <span :class="{ completed: todo.completed }">{{ todo.title }}</span>
        <button @click="deleteTodo(todo.id)">删除</button>
      </li>
    </ul>

    <!-- 加载状态提示 -->
    <p v-if="loading">加载中...</p>
    <p v-if="error" style="color: red;">{{ error }}</p>
  </div>
</template>

<script>
import axios from 'axios';

// 后端 API 基础 URL（根据你的后端实际地址修改）
const API_BASE_URL = 'http://localhost:8080/api/todos';

export default {
  data() {
    return {
      todos: [],
      newTodo: '',
      loading: false,
      error: null
    };
  },
  mounted() {
    this.fetchTodos();
  },
  methods: {
    // 获取所有待办
    async fetchTodos() {
      this.loading = true;
      this.error = null;
      try {
        const response = await axios.get(API_BASE_URL);
        this.todos = response.data;
      } catch (err) {
        this.error = '获取待办失败';
        console.error(err);
      } finally {
        this.loading = false;
      }
    },

    // 添加待办
    async addTodo() {
      if (!this.newTodo.trim()) return;
      try {
        const response = await axios.post(API_BASE_URL, {
          title: this.newTodo
        });
        // 将新添加的待办加入列表
        this.todos.push(response.data);
        this.newTodo = '';
      } catch (err) {
        this.error = '添加待办失败';
        console.error(err);
      }
    },

    // 更新待办（修改完成状态或标题，这里只更新完成状态，你可以扩展标题编辑）
    async updateTodo(todo) {
      try {
        await axios.put(`${API_BASE_URL}/${todo.id}`, todo);
        // 更新本地状态（无需额外操作，因为 v-model 已经修改了 todo.completed）
      } catch (err) {
        this.error = '更新待办失败';
        console.error(err);
        // 如果失败，重新获取列表以恢复正确状态
        this.fetchTodos();
      }
    },

    // 删除待办
    async deleteTodo(id) {
      try {
        await axios.delete(`${API_BASE_URL}/${id}`);
        // 从本地列表中移除
        this.todos = this.todos.filter(todo => todo.id !== id);
      } catch (err) {
        this.error = '删除待办失败';
        console.error(err);
      }
    }
  }
};
</script>

<style>
.completed {
  text-decoration: line-through;
  color: gray;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  margin: 8px 0;
}
button {
  margin-left: 8px;
}
</style>