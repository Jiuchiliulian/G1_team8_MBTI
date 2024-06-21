import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _167ddd7f = () => interopDefault(import('..\\pages\\register.vue' /* webpackChunkName: "pages/register" */))
const _4e5038ca = () => interopDefault(import('..\\pages\\resetPwd.vue' /* webpackChunkName: "pages/resetPwd" */))
const _b44abe5c = () => interopDefault(import('..\\pages\\userlogin.vue' /* webpackChunkName: "pages/userlogin" */))
const _4600bd17 = () => interopDefault(import('..\\pages\\wxamp.vue' /* webpackChunkName: "pages/wxamp" */))
const _11ad2eec = () => interopDefault(import('..\\pages\\User\\management.vue' /* webpackChunkName: "pages/User/management" */))
const _9d961d60 = () => interopDefault(import('..\\pages\\User\\profile\\index.vue' /* webpackChunkName: "pages/User/profile/index" */))
const _b5180150 = () => interopDefault(import('..\\pages\\User\\publish.vue' /* webpackChunkName: "pages/User/publish" */))
const _fd859b40 = () => interopDefault(import('..\\pages\\User\\profile\\resetPwd.vue' /* webpackChunkName: "pages/User/profile/resetPwd" */))
const _0aa37676 = () => interopDefault(import('..\\pages\\User\\profile\\userAvatar.vue' /* webpackChunkName: "pages/User/profile/userAvatar" */))
const _2cdc048f = () => interopDefault(import('..\\pages\\User\\profile\\userEmail.vue' /* webpackChunkName: "pages/User/profile/userEmail" */))
const _e7eb36ea = () => interopDefault(import('..\\pages\\User\\profile\\userInfo.vue' /* webpackChunkName: "pages/User/profile/userInfo" */))
const _a618d9b4 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))
const _33c1db54 = () => interopDefault(import('..\\pages\\c\\_contentId\\index.vue' /* webpackChunkName: "pages/c/_contentId/index" */))
const _b0f73c8a = () => interopDefault(import('..\\pages\\email-validate\\_uuid\\index.vue' /* webpackChunkName: "pages/email-validate/_uuid/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/register",
    component: _167ddd7f,
    name: "register"
  }, {
    path: "/resetPwd",
    component: _4e5038ca,
    name: "resetPwd"
  }, {
    path: "/userlogin",
    component: _b44abe5c,
    name: "userlogin"
  }, {
    path: "/wxamp",
    component: _4600bd17,
    name: "wxamp"
  }, {
    path: "/User/management",
    component: _11ad2eec,
    name: "User-management"
  }, {
    path: "/User/profile",
    component: _9d961d60,
    name: "User-profile"
  }, {
    path: "/User/publish",
    component: _b5180150,
    name: "User-publish"
  }, {
    path: "/User/profile/resetPwd",
    component: _fd859b40,
    name: "User-profile-resetPwd"
  }, {
    path: "/User/profile/userAvatar",
    component: _0aa37676,
    name: "User-profile-userAvatar"
  }, {
    path: "/User/profile/userEmail",
    component: _2cdc048f,
    name: "User-profile-userEmail"
  }, {
    path: "/User/profile/userInfo",
    component: _e7eb36ea,
    name: "User-profile-userInfo"
  }, {
    path: "/",
    component: _a618d9b4,
    name: "index"
  }, {
    path: "/c/:contentId",
    component: _33c1db54,
    name: "c-contentId"
  }, {
    path: "/email-validate/:uuid",
    component: _b0f73c8a,
    name: "email-validate-uuid"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
