import type {ReactNode} from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import Heading from '@theme/Heading';

import styles from './index.module.css';

function HomepageHeader() {
  const {siteConfig} = useDocusaurusContext();
  return (
    <header className={clsx(styles.hero)}>
      <div className={clsx('container', styles.heroInner)}>
        <p className={styles.eyebrow}>Android Automotive OS</p>
        <Heading as="h1" className={styles.title}>
          {siteConfig.title}
        </Heading>
        <p className={styles.subtitle}>{siteConfig.tagline}</p>
        <p className={styles.lede}>
          A Material Design 3–class platform purpose-built for the vehicle cabin
          — glanceable, quiet, and safe by default. Dual UI stacks share tokens
          and compliance; OEMs brand without forking.
        </p>
        <div className={styles.actions}>
          <Link className="button button--primary button--lg" to="/guide/get-started">
            Get started
          </Link>
          <Link
            className="button button--outline button--lg"
            to="/docs/vision">
            Read the vision
          </Link>
        </div>
      </div>
    </header>
  );
}

type Pillar = {
  title: string;
  body: string;
};

const pillars: Pillar[] = [
  {
    title: 'Glanceable',
    body: 'Readable in under a second, at speed, in day and night.',
  },
  {
    title: 'Quiet',
    body: 'Fewer elements. Stronger hierarchy. Chrome stays out of the way.',
  },
  {
    title: 'Safe by default',
    body: 'Driving and UX restrictions shape the UI — not a post-hoc checklist.',
  },
  {
    title: 'One language, two skins',
    body: 'Compose and Views share meaning; OEM brand sits on tokens and RROs.',
  },
  {
    title: 'Thin by design',
    body: 'Adopt one module without dragging samples, catalog, or this website.',
  },
];

function Pillars() {
  return (
    <section className={styles.section}>
      <div className="container">
        <Heading as="h2" className={styles.sectionTitle}>
          Product pillars
        </Heading>
        <p className={styles.sectionLede}>
          Every feature must pass these five. Details in the{' '}
          <Link to="/docs/product/pillars">pillars doc</Link>.
        </p>
        <div className={styles.pillarGrid}>
          {pillars.map((item) => (
            <article key={item.title} className={styles.pillar}>
              <Heading as="h3" className={styles.pillarTitle}>
                {item.title}
              </Heading>
              <p>{item.body}</p>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}

function DualStack() {
  return (
    <section className={clsx(styles.section, styles.sectionMuted)}>
      <div className="container">
        <Heading as="h2" className={styles.sectionTitle}>
          Dual stack, dual distribution
        </Heading>
        <div className={styles.split}>
          <div>
            <Heading as="h3" className={styles.pillarTitle}>
              Jetpack Compose
            </Heading>
            <p>
              Modern app and media experiences. Preferred for application
              surfaces once MVP chrome is in place.
            </p>
            <Link to="/docs/platforms/compose">Compose guidelines →</Link>
          </div>
          <div>
            <Heading as="h3" className={styles.pillarTitle}>
              Views / Soong
            </Heading>
            <p>
              SystemUI, status bar, and build-tree apps stay Views-first via
              Soong modules — not Gradle <code>implementation</code> into
              platform images.
            </p>
            <Link to="/docs/platforms/views">Views guidelines →</Link>
          </div>
        </div>
      </div>
    </section>
  );
}

function StatusNote() {
  return (
    <section className={styles.section}>
      <div className="container">
        <Heading as="h2" className={styles.sectionTitle}>
          Early docs shell
        </Heading>
        <p className={styles.sectionLede}>
          This site is a thin production shell. It reads from the repository{' '}
          <code>docs/</code> tree. Interactive catalogs and live demos are{' '}
          <strong>not implemented yet</strong>.
        </p>
        <ul className={styles.linkList}>
          <li>
            <Link to="/docs/design-language/foundations">Foundations</Link>
          </li>
          <li>
            <Link to="/docs/design-language/tokens">Styles / tokens</Link>
          </li>
          <li>
            <Link to="/docs/compliance/">Compliance</Link>
          </li>
          <li>
            <Link to="/docs/adoption/integration">Develop / adoption</Link>
          </li>
          <li>
            <Link to="/guide/components">Components (planned)</Link>
          </li>
          <li>
            <Link to="/docs/roadmap">Roadmap</Link>
          </li>
        </ul>
      </div>
    </section>
  );
}

export default function Home(): ReactNode {
  const {siteConfig} = useDocusaurusContext();
  return (
    <Layout
      title="Design language for the automobile cabin"
      description={siteConfig.tagline}>
      <HomepageHeader />
      <main>
        <Pillars />
        <DualStack />
        <StatusNote />
      </main>
    </Layout>
  );
}
