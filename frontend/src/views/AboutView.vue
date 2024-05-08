<template>
  <div class="about">
    <h1>{{str}}</h1>
  </div>
</template>

<script setup>

import axios from "axios";
import {ref} from "vue";

const str = ref("");

axios
    .get("api/menus/store/1")
    .then((res) => {
      console.log("성공");
      console.log(res.data);
      str.value = res.data;
    })
    .catch((err) => {
      console.error(err);
    });

</script>
