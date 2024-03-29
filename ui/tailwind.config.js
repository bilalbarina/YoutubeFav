/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}"
  ],
  theme: {
    extend: {
      container: {
        center: true,
      },
      colors: {
        primary: '#ef4444'
      }
    },
  },
  plugins: [require('@tailwindcss/forms')],
}
