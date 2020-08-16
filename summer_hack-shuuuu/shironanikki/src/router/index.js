/*jshint esversion: 6 */
/*jshint es5: false */
import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store';

import Top from '../views/Top.vue';
import Home from '../views/Home.vue';
// import Profile from '../views/Profile.vue';
import Test from '../views/Test.vue';
import Signup from '../views/Signup.vue';
import Signin from '../views/Signin.vue';
import SetUser from '../views/SetUser.vue';
import SetContent from '../views/SetContent.vue';
import SetCalendar from '../views/SetCalendar.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Top',
    component: Top
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {
      authRequired: true
    }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    meta: {
      authRequired: true
    }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import(/* webpackChunkName: "about" */ '../views/Profile.vue'),
    meta: {
      authRequired: true
    }
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
    meta: {
      authRequired: true
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  },
  {
    path: '/setuser',
    name: 'SetUser',
    component: SetUser,
    meta: {
      authRequired: true
    }
  },
  {
    path: '/setcontent',
    name: 'SetContent',
    component: SetContent,
    meta: {
      authRequired: true
    }
  },
  {
    path: '/setcalendar',
    name: 'SetCalendar',
    component: SetCalendar,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.authRequired)) {
    if (!store.state.isAuthenticated) {
      next({
        path: "/"
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
