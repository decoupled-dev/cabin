import type {SidebarsConfig} from '@docusaurus/plugin-content-docs';

/**
 * Thin docs shell IA — subset of repo `docs/` aligned with site-plan.md.
 * Full catalog / interactive demos are not in this shell.
 */
const sidebars: SidebarsConfig = {
  foundationsSidebar: [
    {
      type: 'category',
      label: 'Foundations',
      collapsed: false,
      items: [
        'design-language/foundations',
        'design-language/README',
        'product/pillars',
        'product/stance',
      ],
    },
  ],

  stylesSidebar: [
    {
      type: 'category',
      label: 'Styles',
      collapsed: false,
      items: [
        'design-language/tokens',
        'design-language/token-schema',
      ],
    },
  ],

  complianceSidebar: [
    {
      type: 'category',
      label: 'Compliance',
      collapsed: false,
      link: {
        type: 'doc',
        id: 'compliance/README',
      },
      items: [
        'compliance/restriction-states',
        'compliance/driving-restrictions',
        'compliance/ux-restrictions',
        'compliance/accessibility-glanceability',
        'compliance/safety-critical',
      ],
    },
  ],

  developSidebar: [
    {
      type: 'category',
      label: 'Platforms',
      collapsed: false,
      items: [
        'platforms/compose',
        'platforms/views',
        'platforms/soong',
      ],
    },
    {
      type: 'category',
      label: 'Adoption',
      collapsed: false,
      items: [
        'adoption/integration',
        'adoption/build-tree',
        'adoption/packaging',
        'adoption/migration',
      ],
    },
    {
      type: 'category',
      label: 'Reference',
      collapsed: true,
      items: [
        'architecture',
        'api-contracts',
        'mvp',
        'pre-implementation',
        'support-matrix',
        'website/site-plan',
        'contributing',
      ],
    },
  ],
};

export default sidebars;
