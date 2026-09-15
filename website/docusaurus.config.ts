import {themes as prismThemes} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

/**
 * Cabin public docs site.
 *
 * Content source of truth remains the repo `docs/` tree. This package is a
 * thin static shell — it must not depend on Android library modules
 * (`cabin-tokens`, `cabin-compliance`, Compose/Views kits, Gradle artifacts).
 */
const config: Config = {
  title: 'Cabin',
  tagline:
    'Open-source AAOS design language and dual UI kit — Compose + Views, compliance-first.',
  favicon: 'img/favicon.svg',

  url: 'https://decoupled-dev.github.io',
  baseUrl: '/cabin/',

  organizationName: 'decoupled-dev',
  projectName: 'cabin',

  // Repo docs intentionally link to AGENTS.md, LICENSE, skills/, etc. outside
  // docs/. Those resolve on GitHub; warn here rather than failing the shell build.
  onBrokenLinks: 'warn',

  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  markdown: {
    format: 'detect',
    hooks: {
      onBrokenMarkdownLinks: 'warn',
    },
  },

  presets: [
    [
      'classic',
      {
        docs: {
          path: '../docs',
          sidebarPath: './sidebars.ts',
          editUrl: 'https://github.com/decoupled-dev/cabin/tree/main/docs/',
          showLastUpdateTime: false,
        },
        blog: {
          showReadingTime: false,
          blogTitle: 'Versions & notes',
          blogDescription: 'Release notes, migration notes, and roadmap highlights.',
          blogSidebarTitle: 'Recent',
          blogSidebarCount: 'ALL',
          onInlineTags: 'warn',
          onInlineAuthors: 'warn',
          onUntruncatedBlogPosts: 'ignore',
          feedOptions: {
            type: 'all',
            xslt: true,
          },
        },
        theme: {
          customCss: './src/css/custom.css',
        },
      } satisfies Preset.Options,
    ],
  ],

  plugins: [
    [
      '@docusaurus/plugin-content-docs',
      {
        id: 'shell',
        path: 'shell-docs',
        routeBasePath: 'guide',
        sidebarPath: './sidebarsShell.ts',
        editUrl: 'https://github.com/decoupled-dev/cabin/tree/main/website/shell-docs/',
      },
    ],
  ],

  themeConfig: {
    image: 'img/social-card.svg',
    colorMode: {
      defaultMode: 'light',
      respectPrefersColorScheme: true,
    },
    docs: {
      sidebar: {
        hideable: true,
      },
    },
    navbar: {
      title: 'Cabin',
      logo: {
        alt: 'Cabin',
        src: 'img/logo.svg',
      },
      items: [
        {
          type: 'docSidebar',
          sidebarId: 'foundationsSidebar',
          label: 'Foundations',
          position: 'left',
        },
        {
          type: 'docSidebar',
          sidebarId: 'stylesSidebar',
          label: 'Styles',
          position: 'left',
        },
        {
          to: '/guide/components',
          label: 'Components',
          position: 'left',
        },
        {
          type: 'docSidebar',
          sidebarId: 'complianceSidebar',
          label: 'Compliance',
          position: 'left',
        },
        {
          type: 'docSidebar',
          sidebarId: 'developSidebar',
          label: 'Develop',
          position: 'left',
        },
        {
          to: '/blog',
          label: 'Versions',
          position: 'left',
        },
        {
          href: 'https://github.com/decoupled-dev/cabin',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Learn',
          items: [
            {label: 'Vision', to: '/docs/vision'},
            {label: 'Foundations', to: '/docs/design-language/foundations'},
            {label: 'Tokens', to: '/docs/design-language/tokens'},
            {label: 'Compliance', to: '/docs/compliance/'},
          ],
        },
        {
          title: 'Develop',
          items: [
            {label: 'Compose', to: '/docs/platforms/compose'},
            {label: 'Views', to: '/docs/platforms/views'},
            {label: 'Soong / build-tree', to: '/docs/adoption/build-tree'},
            {label: 'Integration', to: '/docs/adoption/integration'},
          ],
        },
        {
          title: 'Project',
          items: [
            {label: 'Roadmap', to: '/docs/roadmap'},
            {label: 'Site plan', to: '/docs/website/site-plan'},
            {label: 'GitHub', href: 'https://github.com/decoupled-dev/cabin'},
            {
              label: 'Apache 2.0',
              href: 'https://github.com/decoupled-dev/cabin/blob/main/LICENSE',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Decoupled. Cabin docs — Apache 2.0.`,
    },
    prism: {
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
      additionalLanguages: ['kotlin', 'groovy', 'bash', 'json'],
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
