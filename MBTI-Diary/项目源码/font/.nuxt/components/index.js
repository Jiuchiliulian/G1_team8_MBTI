export { default as CampusSide } from '../..\\components\\CampusSide.vue'
export { default as Comment } from '../..\\components\\Comment.vue'
export { default as CommentChild } from '../..\\components\\CommentChild.vue'
export { default as CommentUser } from '../..\\components\\CommentUser.vue'
export { default as Content } from '../..\\components\\Content.vue'
export { default as ContentBottom } from '../..\\components\\ContentBottom.vue'
export { default as ContentFile } from '../..\\components\\ContentFile.vue'
export { default as Menu } from '../..\\components\\Menu.vue'
export { default as SvgIcon } from '../..\\components\\SvgIcon\\index.vue'

// nuxt/nuxt.js#8607
function wrapFunctional(options) {
  if (!options || !options.functional) {
    return options
  }

  const propKeys = Array.isArray(options.props) ? options.props : Object.keys(options.props || {})

  return {
    render(h) {
      const attrs = {}
      const props = {}

      for (const key in this.$attrs) {
        if (propKeys.includes(key)) {
          props[key] = this.$attrs[key]
        } else {
          attrs[key] = this.$attrs[key]
        }
      }

      return h(options, {
        on: this.$listeners,
        attrs,
        props,
        scopedSlots: this.$scopedSlots,
      }, this.$slots.default)
    }
  }
}
