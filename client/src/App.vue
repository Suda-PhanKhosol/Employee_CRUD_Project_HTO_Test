<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>System</span>
        <span class="font-weight-light"> Employee Management</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://www.facebook.com/ploysuda.phankhosol/"
        target="_blank"
      >
        <span class="mr-2">Username : {{username}}</span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    username: 'Suda  Phankhosol',
  }),
};
</script>
