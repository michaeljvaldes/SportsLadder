import { FrontendSportsLadderPage } from './app.po';

describe('frontend-sports-ladder App', () => {
  let page: FrontendSportsLadderPage;

  beforeEach(() => {
    page = new FrontendSportsLadderPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
