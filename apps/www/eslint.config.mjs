import { dirname } from "path";
import { fileURLToPath } from "url";
import nextVitals from "eslint-config-next/core-web-vitals";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const eslintConfig = [
  ...nextVitals,
  {
    ignores: [".next/**", "node_modules/**", "out/**", `${__dirname}/.next/**`],
  },
];

export default eslintConfig;
